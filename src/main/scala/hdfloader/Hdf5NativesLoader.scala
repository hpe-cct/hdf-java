/*
 * (c) Copyright [2016] Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hdfloader

import java.io.{FileOutputStream, File}
import java.nio.channels.Channels
import java.nio.file.{Path, Files}

import ncsa.hdf.hdf5lib.H5

/** Make sure the native libraries needed for HDF5 file creation and reading are loaded.
  *
  * This uses the same strategy as OpenCLNativesLoader in the cogx.platform.opencl package.
  */
object Hdf5NativesLoader {
  /** Guard variable to prevent multiple extractions of the libraries. */
  private var loaded = false

  /** Make sure necessary native libraries are loaded.
    *
    * This method should be called before the JVM reaches any references to the
    * H5 APIs. The first load() call will instantiate this object and perform the action.
    * Subsequent calls will do nothing (as desired).
    */
  def load(): Unit = {
    synchronized {
      if (loaded)
        return

      // The hdf5 native library file.
      val jhdf5Lib = System.mapLibraryName("jhdf5")

      // Extract the appropriate native library file to a temporary directory.
      val dir = extractResourcesToTempDir("hdf5", "hdfloader", List(jhdf5Lib))

      // Absolute pathname to the hdf5 native library
      val jhdf5LibPath = new File(dir + File.separator + jhdf5Lib).getAbsolutePath

      // Set the System property looked at by the H5 loader to the library pathname
      System.setProperty("ncsa.hdf.hdf5lib.H5.hdf5lib", jhdf5LibPath)

      // This is technically not needed- the H5 object will call this upon first use also.
      H5.loadH5Lib()

      // Set the guard flag to prevent re-loading
      loaded = true
    }
  }

  /** Extract files from a resource jar to a temporary directory.
    *
    * The temporary directory is set to be deleted when the JVM exits.
    *
    * @param tmpDirId  An id to become part of the temp folder name, just to help
    *                  identify its contents
    * @param subfolder Subdirectory of resources dir to extract files from
    * @param files     List of files to extract to a temporary directory
    * @return The temporary directory where files were extracted.
    */
  private def extractResourcesToTempDir(tmpDirId: String, subfolder: String, files: List[String]): Path = {
    val loader = getClass.getClassLoader

    val dir = Files.createTempDirectory("cog-" + subfolder + "-")
    dir.toFile.deleteOnExit()

    for (f <- files) {
      val in = loader.getResourceAsStream(subfolder + "/" + f)
      val outFile = new File(dir + "/" + f)
      outFile.deleteOnExit()
      val out = new FileOutputStream(outFile)

      out.getChannel.transferFrom(Channels.newChannel(in), 0, Long.MaxValue)

      in.close()
      out.close()
    }

    dir
  }
}