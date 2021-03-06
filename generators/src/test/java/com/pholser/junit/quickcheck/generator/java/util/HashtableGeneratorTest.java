/*
 The MIT License

 Copyright (c) 2010-2013 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.generator.java.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashtableGeneratorTest {
    private HashtableGenerator generator;

    @Before public void setUp() {
        generator = new HashtableGenerator();
    }

    @Test public void startsOutWithAnEmptyMap() {
        assertTrue(generator.emptyMap().isEmpty());
    }

    @Test public void disallowsNullKeyAndNullValue() {
        assertFalse(generator.okToAdd(null, null));
    }

    @Test public void disallowsNullKey() {
        assertFalse(generator.okToAdd(null, new Object()));
    }

    @Test public void disallowsNullValue() {
        assertFalse(generator.okToAdd(new Object(), null));
    }

    @Test public void allowsKeyAndValueWhenNeitherIsNull() {
        assertTrue(generator.okToAdd(new Object(), new Object()));
    }
}
