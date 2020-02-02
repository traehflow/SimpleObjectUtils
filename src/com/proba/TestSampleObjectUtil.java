package com.proba;

import com.proba.dimtest.annotations.Test;

import static com.proba.dimtest.utils.DimTestUtil.assertFalse;
import static com.proba.dimtest.utils.DimTestUtil.assertTrue;
public class TestSampleObjectUtil {

    SampleDTO sd1;
    SampleDTO sd2;
    SampleDTO sd3;


    public TestSampleObjectUtil() {
        sd1 = new SampleDTO();
        sd1.setField1("v1");
        sd1.setField2("v2");
        sd1.setField3("v3");
        sd1.setIntField(42);
        sd2 = new SampleDTO();
        sd2.setField1("v1");
        sd2.setField2("v2");
        sd2.setField3("v3");
        sd2.setIntField(42);
        sd3 = new SampleDTO();
        sd3.setField1("zv1");
        sd3.setField2("zv2");
        sd3.setField3("zv3");
        sd3.setIntField(942);
    }

    @Test
    public void testEquals1() {
        assertTrue(SimpleObjectUtil.equalsDtoObjects(sd1, sd2));
    }

    @Test
    public void testEquals2() {
        assertFalse(SimpleObjectUtil.equalsDtoObjects(sd1, sd3));
    }

    @Test
    public void testEquals3() {
        assertFalse(SimpleObjectUtil.equalsDtoObjects(sd1, null));
    }

    @Test
    public void testEquals4() {
        assertFalse(SimpleObjectUtil.equalsDtoObjects(null, sd2));
    }

    @Test
    public void testEquals5() {
        assertTrue(SimpleObjectUtil.equalsDtoObjects(null, null));
    }

    @Test
    public void testEquals6() {
        assertTrue(SimpleObjectUtil.equalsDtoObjects(sd1, sd1));
    }
}
