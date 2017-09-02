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
import preclass.algorithm.AudioData;

/**
 *
 * @author farias-i3
 */
public class Paso2_Good {

    public static void main(String[] args) throws Exception {

        AudioSource as = new AudioBuffer(AudioData.S, AudioData.SAMPLE_RATE);
        Window window = new HammingWindow(as, 10, 10, false);

        System.err.println(as);
        System.err.println(window);

        double[] buf = new double[window.getFrameSize()];

        while (window.read(buf)) {
        }

        for (double swingValue : buf) {
            System.out.println(swingValue + ",");
        }

    }
}
