/*
 * Copyright 2015 Skymind,Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.nd4j.linalg.util;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Linear algebra exceptions
 *
 * @author Adam Gibson
 */
public class LinAlgExceptions {

    public static void assertSameShape(INDArray n, INDArray n2) {
        if (!Shape.shapeEquals(n.shape(), n2.shape()))
            throw new IllegalStateException("Mis matched shapes");
    }

    public static void assertRows(INDArray n, INDArray n2) {
        if (n.rows() != n2.rows())
            throw new IllegalStateException("Mis matched rows");
    }


    /**
     * Asserts matrix multiply rules (columns of left == rows of right)
     *
     * @param nd1 the left ndarray
     * @param nd2 the right ndarray
     */
    public static void assertMultiplies(INDArray nd1, INDArray nd2) {
        if (nd1.columns() != nd2.rows())
            throw new IllegalStateException("Column of left " + nd1.columns() + " != rows of right " + nd2.rows());
    }


    public static void assertColumns(INDArray n, INDArray n2) {
        if (n.columns() != n2.columns())
            throw new IllegalStateException("Mis matched rows");
    }

    public static void assertValidNum(INDArray n) {
        INDArray linear = n.linearView();
        for (int i = 0; i < linear.length(); i++) {
            double d = linear.getDouble(i);
            if (Double.isNaN(d) || Double.isInfinite(d))
                throw new IllegalStateException("Found infinite or nan");

        }
    }

}
