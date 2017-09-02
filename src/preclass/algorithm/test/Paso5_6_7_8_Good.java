/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com>.
 */
package preclass.algorithm.test;

import preclass.algorithm.AudioData;
import preclass.algorithm.AutoCorrelation;

public class Paso5_6_7_8_Good {

    public static void main(String[] args) throws Exception {

        AutoCorrelation ac = new AutoCorrelation(AudioData.ERROR, AudioData.SAMPLE_RATE);

        double[] result = ac.getResult();

        for (int j = 0; j < result.length; j++) {
            if (j < 6 || j > result.length - 7) {
                //System.out.println(j + " " + firf.getResult()[j]);
            } else {
                //System.out.println(j + " " + firf.getResult()[j]);
                //System.out.println(firf.getResult()[j] + ",");
            }
            //System.out.println(j + " " + firf.getResult()[j]);
            //System.out.println(result[j] + ",");
        }

        System.err.println(ac.getMaxPos());
        System.err.println(ac.getMaxValue());
        System.err.println(ac.getDx());
        System.err.println("T0 y F0");
        System.err.println(ac.getT0());
        System.err.println(ac.getF0());
    }
}
