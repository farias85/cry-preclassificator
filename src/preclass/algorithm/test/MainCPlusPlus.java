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

import de.fau.cs.jstk.framed.HammingWindow;
import de.fau.cs.jstk.framed.Window;
import de.fau.cs.jstk.sampled.AudioBuffer;
import de.fau.cs.jstk.sampled.AudioSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import preclass.algorithm.AudioData;
import preclass.algorithm.AutoCorrelation;
import preclass.algorithm.FIRFilterJSTK;
import preclass.algorithm.FIRFilterSimple;
import preclass.algorithm.LinearPredictiveCoding;

public class MainCPlusPlus {

    public static void main(String[] args) {
        try {

            final int FS = 8000;

            AudioSource originalSignal = new AudioBuffer(AudioData.S, FS);
            Window window = new HammingWindow(originalSignal, 10, 10, false);

            double[] resultHamming = new double[window.getFrameSize()];
            window.read(resultHamming);

            double[] coeffLPC = new LinearPredictiveCoding().createPlan(resultHamming);

            AudioSource swin = new AudioBuffer(resultHamming, FS);
            FIRFilterJSTK filter = new FIRFilterSimple(swin, coeffLPC, 128, true);

            double[] error = new double[resultHamming.length];
            filter.read(error, error.length);

            double[] filterResult = filter.getResult();

            AutoCorrelation ac = new AutoCorrelation(filterResult, FS);
            //double[] result = ac.getResult();

            System.err.println(ac.getMaxPos());
            System.err.println(ac.getMaxValue());
            System.err.println("dx " + ac.getDx());
            System.err.println("T0 y F0");
            System.err.println(ac.getT0());
            System.err.println(ac.getF0());

        } catch (IOException ex) {
            Logger.getLogger(MainJava.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
