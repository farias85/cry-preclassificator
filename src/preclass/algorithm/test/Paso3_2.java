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

import java.io.IOException;
import preclass.algorithm.AudioData;
import preclass.algorithm.AutoCorrelation;
import preclass.algorithm.LinearPredictor;

public class Paso3_2 {

    public static void main(String[] args) throws IOException {

        LinearPredictor lp = new LinearPredictor(12);

        AutoCorrelation ac = new AutoCorrelation(AudioData.S_WINDOW, AudioData.SAMPLE_RATE);

        double[] acResult = ac.getResult();
        double[] acMitad = new double[80];

        System.arraycopy(acResult, acResult.length / 2, acMitad, 0, acMitad.length);

        double[] resultFilter = lp.getARFilter(acMitad);

        double[] a = lp.reflectionCoeffsToARParameters(resultFilter, 12);

        for (double d : a) {
            System.out.println(d);
        }
    }
}
