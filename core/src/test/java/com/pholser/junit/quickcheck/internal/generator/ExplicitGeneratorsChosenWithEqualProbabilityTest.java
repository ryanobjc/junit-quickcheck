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

package com.pholser.junit.quickcheck.internal.generator;

import java.lang.reflect.Type;
import java.util.List;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class ExplicitGeneratorsChosenWithEqualProbabilityTest extends CoreTheoryParameterTest {
    @Override protected void primeSourceOfRandomness() {
        when(randomForParameterGenerator.nextInt(0, 2)).thenReturn(0).thenReturn(1).thenReturn(2);
    }

    @SuppressWarnings("unchecked")
    @Override protected Class<? extends Generator>[] explicitGenerators() {
        return new Class[] { FooGenerator.class, BarGenerator.class, BazGenerator.class };
    }

    @Override protected Type parameterType() {
        return String.class;
    }

    @Override protected int sampleSize() {
        return 3;
    }

    @Override protected List<?> randomValues() {
        return asList("foo", "bar", "baz");
    }

    @Override public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator, times(3)).nextInt(0, 2);
    }

    public static class FooGenerator extends Generator<String> {
        public FooGenerator() {
            super(String.class);
        }

        @Override public String generate(SourceOfRandomness random, GenerationStatus status) {
            return "foo";
        }
    }

    public static class BarGenerator extends Generator<String> {
        public BarGenerator() {
            super(String.class);
        }

        @Override public String generate(SourceOfRandomness random, GenerationStatus status) {
            return "bar";
        }
    }

    public static class BazGenerator extends Generator<String> {
        public BazGenerator() {
            super(String.class);
        }

        @Override public String generate(SourceOfRandomness random, GenerationStatus status) {
            return "baz";
        }
    }
}
