package simpledb.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import simpledb.storage.Field;
import simpledb.storage.RecordId;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */
public class Tuple implements Serializable {
	private TupleDesc tuple_schema;
	private ArrayList<Field> tuple_Fields;
	private RecordId recordId;
    private static final long serialVersionUID = 1L;

    /**
     * Create a new tuple with the specified schema (type).
     *
     * @param td
     *            the schema of this tuple. It must be a valid TupleDesc
     *            instance with at least one field.
     */
    public Tuple(TupleDesc td) {
        // some code goes here
    	this.tuple_schema = td;
    	//System.out.println("td len:"+td.numFields());
    	this.tuple_Fields = new ArrayList<Field>(td.numFields());
    	for (int i = 0; i <= td.numFields() - 1; i++) {
            this.tuple_Fields.add(null);
        }
    }

    /**
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here
        return this.tuple_schema;
    }

    /**
     * @return The RecordId representing the location of this tuple on disk. May
     *         be null.
     */
    public RecordId getRecordId() {
        // some code goes here
        return this.recordId;
    }

    /**
     * Set the RecordId information for this tuple.
     *
     * @param rid
     *            the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        // some code goes here
    	this.recordId = rid;
    }

    /**
     * Change the value of the ith field of this tuple.
     *
     * @param i
     *            index of the field to change. It must be a valid index.
     * @param f
     *            new value for the field.
     */
    public void setField(int i, Field f) {
        // some code goes here
    	//System.out.println("i£º"+i+"tuple length:"+tuple_Fields.size());
    	this.tuple_Fields.set(i, f);
    		
    }

    /**
     * @return the value of the ith field, or null if it has not been set.
     *
     * @param i
     *            field index to return. Must be a valid index.
     */
    public Field getField(int i) {
        // some code goes here
    	return this.tuple_Fields.get(i);
    	
    }

    /**
     * Returns the contents of this Tuple as a string. Note that to pass the
     * system tests, the format needs to be as follows:
     *
     * column1\tcolumn2\tcolumn3\t...\tcolumnN
     *
     * where \t is any whitespace (except a newline)
     */
    public String toString() {
        // some code goes here
    	 String row = new String();
         for (int i = 0; i <= this.tuple_Fields.size() - 2; i++) {
             row += this.tuple_Fields.get(i).toString() + "\t";
         }
         row += this.tuple_Fields.get(tuple_Fields.size() - 1) + "\n";
         return row;
//        throw new UnsupportedOperationException("Implement this");
    }

    /**
     * @return
     *        An iterator which iterates over all the fields of this tuple
     * */
    public Iterator<Field> fields()
    {
        // some code goes here
        return tuple_Fields.iterator();
    }

    /**
     * reset the TupleDesc of this tuple (only affecting the TupleDesc)
     * */
    public void resetTupleDesc(TupleDesc td)
    {
        // some code goes here
    	this.tuple_schema = td;
//        this.tuple_Fields = new ArrayList<Field>(td.numFields());
//        for (int i = 0; i <= td.numFields() - 1; i++) {
//            this.tuple_Fields.add(null);
//        }
    }
}
