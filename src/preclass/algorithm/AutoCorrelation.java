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
package preclass.algorithm;

import de.fau.cs.jstk.sampled.AudioBuffer;
import de.fau.cs.jstk.sampled.AudioSource;
import java.io.IOException;

public class AutoCorrelation {

    /**
     * El resultado de la autocorrelacion
     */
    private double[] result;
    /**
     * Se sabe que la autocorrelación es simétrica, entonces partiendo del centro
     * se busca el valor y la posición del máximo local
     */
    private int maxPos = 0;
    private double maxValue = 0;
    /**
     * Desplazamiento con relación a la posición del valor máximo
     */
    private double dx;
    /**
     * Período fundamental
     */
    private double T0;
    /**
     * Frecuencia fundamental F0
     */
    private double F0;

    public AutoCorrelation(double[] audioData, int sampleRate) throws IOException {
        ac(audioData, sampleRate);
    }

    /**
     * Compute the autocorrelation algorithm
     */
    private void ac(double[] audioData, int sampleRate) throws IOException {

        AudioSource as = new AudioBuffer(audioData, sampleRate);

        double[] dataReverse = new double[audioData.length];

        int k = 0;
        for (int i = dataReverse.length - 1; i >= 0; i--) {
            dataReverse[k++] = audioData[i];
        }

        double[] fir = dataReverse;
        FIRFilterJSTK firf = new FIRFilterSimple(as, fir, 128, false);

        double[] buf = new double[audioData.length];
        firf.read(buf, buf.length);

        result = firf.getResult();
        double max = 0;

        for (int i = 0; i < result.length; i++) {
            double d = result[i];
            if (d > max) {
                max = d;
            }
        }

        for (int i = 0; i < result.length; i++) {
            result[i] /= max;
            if (i > (result.length / 2) + 1) {
                if (result[i] > maxValue) {
                    maxValue = result[i];
                    maxPos = i;
                }
            }
        }

        double denom = (2 * (result[maxPos + 1] + result[maxPos - 1] - 2 * result[maxPos]));
        dx = -(result[maxPos + 1] - result[maxPos - 1]) / denom;

        T0 = (maxPos - ((result.length + 1) / 2 - 1) + dx) / sampleRate;
        F0 = 1 / T0;
    }

    public double getF0() {
        return F0;
    }

    public double getT0() {
        return T0;
    }

    public double getDx() {
        return dx;
    }

    public int getMaxPos() {
        return maxPos;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double[] getResult() {
        return result;
    }
}
