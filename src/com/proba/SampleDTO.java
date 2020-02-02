package com.proba;

import java.lang.reflect.Field;
import java.util.Objects;

public class SampleDTO extends BaseDTO {
    private static int allCounterSomething = 0;
    private final String SOMETHING_FINAL = "FiNAL_VALUE";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SampleDTO)) return false;
        SampleDTO sampleDTO = (SampleDTO) o;
        return intField == sampleDTO.intField &&
                field1.equals(sampleDTO.field1) &&
                field2.equals(sampleDTO.field2) &&
                field3.equals(sampleDTO.field3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3, intField);
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }


    protected Object getValue(Field field) throws IllegalAccessException {

            return field.get(this);


    }

    private String field1;
    private String field2;
    private String field3;

    private int intField;

    public static void main(String args[]) {
        SampleDTO sd1 = new SampleDTO();
        sd1.setField1("v1");
        sd1.setField2("v2");
        sd1.setField3("v3");
        sd1.setIntField(42);
        SampleDTO sd2 = new SampleDTO();
        sd2.setField1("v1");
        sd2.setField2("v2");
        sd2.setField3("v3");
        sd2.setIntField(42);
        SampleDTO sd3 = new SampleDTO();
        sd3.setField1("zv1");
        sd3.setField2("zv2");
        sd3.setField3("zv3");
        sd3.setIntField(942);
        System.out.println(SimpleObjectUtil.dtoToString(sd1));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(sd1, sd2));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(sd1, sd3));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(sd1, null));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(null, sd2));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(null, null));
        System.out.println(SimpleObjectUtil.equalsDtoObjects(sd1, sd1));

        System.out.println(sd1);
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }
}
