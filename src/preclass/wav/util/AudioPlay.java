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
package preclass.wav.util;

import de.fau.cs.jstk.sampled.AudioFileReader;
import de.fau.cs.jstk.sampled.AudioSource;

public class AudioPlay {

    public static void main(String[] args) throws Exception {

        //String file = "C:/y4.wav";
        String file = "C:/y5true.wav";
        System.err.println("Now playing " + file);

        AudioSource reader = new AudioFileReader(file, true);
        reader.setPreEmphasis(false, 1);
        de.fau.cs.jstk.sampled.AudioPlay play = new de.fau.cs.jstk.sampled.AudioPlay(reader);

        // play whole file
        while (play.write() > 0);
    }
}
