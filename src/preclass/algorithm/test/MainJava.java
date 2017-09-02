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
import preclass.algorithm.LinearPredictor;

/**
 * The algorithm shows some adaptation to SIFT (Simple Inverse Filter Tracking) block
 * diagram. SIFT is a classic algorithm used for speech pitch (Fo) estimation. 
 * The adaptation allows us the pitch estimation in pain cry signal from neonates. 
 * These cry vocalizations have a normal pitch between 400 and 600 hertz. Cry pitch 
 * range is more than speech. The implementation of thispaper changes the cut off 
 * frequency from prefiltering in classic block diagram according to cry signal. 
 * Classic algorithm makes automatic decision about the unvoiced and voiced frames 
 * where the pitch is estimated. In the paper, the pitch is estimated from voiced 
 * frames through manual procedure. That apparent disadvantage allows an efficient 
 * selection of voiced frames. The procedure used considers signal windowing without 
 * overlap. The procedure also exclude decimate avoiding downsampling and affectation 
 * of signal spectrum. The modification achieved together short duration technique 
 * make an efficient adaptation for cry pitch estimation. 
 */
public class MainJava {

    public static void main(String[] args) {
        try {

            final int FS = 8000;

            AudioSource originalSignal = new AudioBuffer(AudioData.S, FS);
            Window window = new HammingWindow(originalSignal, 10, 10, false);

            double[] resultHamming = new double[window.getFrameSize()];
            window.read(resultHamming);

            LinearPredictor lp = new LinearPredictor(12);
            AutoCorrelation acAux = new AutoCorrelation(AudioData.S_WINDOW, AudioData.SAMPLE_RATE);

            double[] acResult = acAux.getResult();
            double[] acMitad = new double[acResult.length % 2 == 0 ? acResult.length / 2 : (int)(acResult.length / 2) + 1];

            System.arraycopy(acResult, acResult.length / 2, acMitad, 0, acMitad.length);
            double[] resultFilter = lp.getARFilter(acMitad);

            double[] coeffLPC = lp.reflectionCoeffsToARParameters(resultFilter, 12);

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
