package simpledb.execution;

import java.io.IOException;

import simpledb.common.Database;
import simpledb.common.DbException;
import simpledb.common.Utility;
import simpledb.storage.BufferPool;
import simpledb.storage.DbFile;
import simpledb.storage.Tuple;
import simpledb.storage.TupleDesc;
import simpledb.transaction.TransactionAbortedException;
import simpledb.transaction.TransactionId;

/**
 * Inserts tuples read from the child operator into the tableId specified in the
 * constructor
 */
public class Insert extends Operator {

    private static final long serialVersionUID = 1L;
    private final TransactionId t;
    private  OpIterator child;
    private final int tableId;
    private boolean inserted = false;
    /**
     * Constructor.
     *
     * @param t
     *            The transaction running the insert.
     * @param child
     *            The child operator from which to read tuples to be inserted.
     * @param tableId
     *            The table in which to insert tuples.
     * @throws DbException
     *             if TupleDesc of child differs from table into which we are to
     *             insert.
     */
    public Insert(TransactionId t, OpIterator child, int tableId)
            throws DbException {
        // some code goes here
    	this.t = t;
    	this.child = child;
    	this.tableId = tableId;
    	
    }

    public TupleDesc getTupleDesc() {
        // some code goes here
        return Utility.getTupleDesc(1);
    }

    public void open() throws DbException, TransactionAbortedException {
        // some code goes here
    	super.open();
    	child.open();
    }

    public void close() {
        // some code goes here
    	
    	super.close();
    	child.close();
    }

    public void rewind() throws DbException, TransactionAbortedException {
        // some code goes here
    	child.rewind();
    }

    /**
     * Inserts tuples read from child into the tableId specified by the
     * constructor. It returns a one field tuple containing the number of
     * inserted records. Inserts should be passed through BufferPool. An
     * instances of BufferPool is available via Database.getBufferPool(). Note
     * that insert DOES NOT need check to see if a particular tuple is a
     * duplicate before inserting it.
     *
     * @return A 1-field tuple containing the number of inserted records, or
     *         null if called more than once.
     * @see Database#getBufferPool
     * @see BufferPool#insertTuple
     */
    protected Tuple fetchNext() throws TransactionAbortedException, DbException {
        // some code goes here
    	if (!inserted) {
            int count = 0;
            DbFile dbFile = Database.getCatalog().getDatabaseFile(tableId);
            while (child.hasNext()) {
                try {
                    dbFile.insertTuple(t, child.next());
                    count += 1;
                } catch (IOException e) {
                    throw new DbException("Insert: Error: IOException when Inserting");
                }
            }
            inserted = true;
            return Utility.getTuple(new int[]{count}, 1);
        }
        return null;
    }

    @Override
    public OpIterator[] getChildren() {
        // some code goes here
    	return new OpIterator[]{child};
    }

    @Override
    public void setChildren(OpIterator[] children) {
        // some code goes here
    	this.child = children[0];
    }
}
