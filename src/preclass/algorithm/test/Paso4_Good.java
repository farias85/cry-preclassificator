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

import de.fau.cs.jstk.sampled.AudioBuffer;
import de.fau.cs.jstk.sampled.AudioSource;
import preclass.algorithm.AudioData;
import preclass.algorithm.FIRFilterJSTK;
import preclass.algorithm.FIRFilterSimple;

public class Paso4_Good {

    public static void main(String[] args) throws Exception {
        AudioSource as = new AudioBuffer(AudioData.S_WINDOW, AudioData.SAMPLE_RATE);

        double[] fir = AudioData.A;
        FIRFilterJSTK firf = new FIRFilterSimple(as, fir, 128, true);

        double[] buf = new double[AudioData.S_WINDOW.length];

        while (firf.read(buf, buf.length) > 0) {
        }

        for (int j = 0; j < firf.getResult().length; j++) {
            if (j < 6 || j > firf.getResult().length - 7) {
                //System.out.println(j + " " + firf.getResult()[j]);
            } else {
                //System.out.println(j + " " + firf.getResult()[j]);
                //System.out.println(firf.getResult()[j] + ",");
            }
            System.out.println(firf.getResult()[j] + ",");
        }
    }
}
