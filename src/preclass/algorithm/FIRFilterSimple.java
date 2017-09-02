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

public class FIRFilterSimple extends FIRFilterJSTK {

    private final boolean truncate;
    
    public FIRFilterSimple(AudioSource source, double[] fir, int min_fft_coefficients, boolean truncate) {
        super(source, fir, min_fft_coefficients);
        this.truncate = truncate;
    }

    public FIRFilterSimple(AudioSource source, double[] fir, boolean truncate) {
        super(source, fir);
        this.truncate = truncate;
    }

    @Override
    public int read(double[] buf, int length) throws IOException {
        // verify buffer size, re-init if necessary
        if (lastread != length) {
            lastread = length;

            l = truncate ? length : fir.length + length - 1;
            result = new double[l];

            // pad to the next power of 2, min 512
            int min = min_fft_coefficients; // MINIMUM_FFT_COEFFICIENTS;

            while (min <= l) {
                min = min << 1;
            }

            fft_size = min;

            // allocate buffers
            fft_fir = new double[2 * fft_size];
            fft_sig = new double[2 * fft_size];
            ifft = new double[2 * fft_size];

            // prepare filter FFT
            fft = new DoubleFFT_1D(fft_size);
            System.arraycopy(fir, 0, fft_fir, 0, fir.length); // zero padded
            fft.realForwardFull(fft_fir);
        }

        // read from the source
        int r = source.read(buf, length);

        // do the convolution if there is any data
        if (r > 0) {
            // copy data
            for (int i = 0; i < length; ++i) {
                fft_sig[i] = buf[i];
            }

            // zero padding
            for (int i = length; i < fft_sig.length; ++i) {
                fft_sig[i] = 0.;
            }

            // forward FFT
            fft.realForwardFull(fft_sig);

            // complex multiplication of signal and filter
            for (int i = 0; i < fft_size; i++) {
                ifft[2 * i] = fft_sig[2 * i] * fft_fir[2 * i] - fft_sig[2 * i + 1] * fft_fir[2 * i + 1];
                ifft[2 * i + 1] = fft_sig[2 * i] * fft_fir[2 * i + 1] + fft_sig[2 * i + 1] * fft_fir[2 * i];
            }

            // inverse FFT
            fft.complexInverse(ifft, true);

            // real part is convolution result
            for (int i = 0; i < l; i++) {
                result[i] = ifft[2 * i];
            }
        }

        return r;
    }
}
