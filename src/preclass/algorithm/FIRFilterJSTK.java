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

import de.fau.cs.jstk.sampled.AudioSource;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import java.io.IOException;

public abstract class FIRFilterJSTK implements AudioSource {

    public static final int MIN_FFT_COEFFICIENTS = 512;
    protected int min_fft_coefficients;
    protected AudioSource source;
    /** finite impulse response */
    protected double[] fir;
    protected double[] result;

    public double[] getResult() {
        return result;
    }

    public void setResult(double[] result) {
        this.result = result;
    }
    /** resulting overlap (one sample shorter than IR) */
    protected double[] overlap;

    public double[] getOverlap() {
        return overlap;
    }

    public void setOverlap(double[] overlap) {
        this.overlap = overlap;
    }
    /** FFT buffer for IR */
    protected double[] fft_fir;
    /** FFT buffer for signal */
    protected double[] fft_sig;
    /** buffer for inverse FFT */
    protected double[] ifft;
    /** FFT object to transform back and forth */
    protected DoubleFFT_1D fft;
    protected int l = 0;
    protected int fft_size = 0;
    protected int lastread = -1;
    protected double scale = 1.;

    /**
     * Generate a new FIR Filter for the given AudioSource and
     * @param source
     * @param fir
     */
    public FIRFilterJSTK(AudioSource source, double[] fir) {
        this(source, fir, MIN_FFT_COEFFICIENTS);
    }

    public FIRFilterJSTK(AudioSource source, double[] fir, int min_fft_coefficients) {
        this.source = source;
        this.fir = fir;
        this.min_fft_coefficients = min_fft_coefficients;
        this.overlap = new double[fir.length - 1];

        scale();
    }

    /**
     * Compute and set the scale factor via the worst case signal (alternating
     * +1/-1). This results in a sum of the absolute fir values.
     */
    private void scale() {
        scale = 0;
        for (int i = 0; i < fir.length; ++i) {
            scale += Math.abs(fir[i]);
        }
        scale = 1. / scale;
    }

    public int read(double[] buf) throws IOException {
        return read(buf, buf.length);
    }

    public abstract int read(double[] buf, int length) throws IOException ;

    public int getSampleRate() {
        return source.getSampleRate();
    }

    public boolean getPreEmphasis() {
        return source.getPreEmphasis();
    }

    public void setPreEmphasis(boolean applyPreEmphasis, double a) {
        source.setPreEmphasis(applyPreEmphasis, a);
    }

    public void tearDown() throws IOException {
        source.tearDown();
    }
}
