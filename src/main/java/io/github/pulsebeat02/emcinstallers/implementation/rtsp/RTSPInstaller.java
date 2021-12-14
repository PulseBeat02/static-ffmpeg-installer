/**
 * MIT License
 *
 * Copyright (c) 2021 Brandon Li
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.pulsebeat02.emcinstallers.implementation.rtsp;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import io.github.pulsebeat02.emcinstallers.OS;
import io.github.pulsebeat02.emcinstallers.implementation.BaseInstaller;
import java.nio.file.Path;

public final class RTSPInstaller extends BaseInstaller {

  private static final Table<OS, Boolean, String> BITS_64;
  private static final Table<OS, Boolean, String> BITS_32;

  static {
    BITS_64 = ImmutableTable.<OS, Boolean, String>builder()
        .put(OS.LINUX, true,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/linux-arm64")
        .put(OS.LINUX, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/linux-amd64")
        .put(OS.MAC, true,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/darwin-arm64")
        .put(OS.MAC, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/darwin-amd64")
        .put(OS.WINDOWS, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/windows-amd64.exe")
        .put(OS.FREEBSD, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/freebsd-amd64")
        .build();
    BITS_32 = ImmutableTable.<OS, Boolean, String>builder()
        .put(OS.LINUX, true,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/linux-arm")
        .put(OS.LINUX, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/linux-ia32")
        .put(OS.WINDOWS, false,
            "https://github.com/MinecraftMediaLibrary/rtsmp-binaries/raw/main/executables/windows-ia32.exe")
        .build();
  }

  RTSPInstaller(final Path folder) {
    super(folder, "simple-rtsp-server", BITS_32, BITS_64);
  }

  RTSPInstaller() {
    super("simple-rtsp-server", BITS_32, BITS_64);
  }

  /**
   * Constructs a new FFmpegInstaller with the specified directory for the executable.
   *
   * @param executable directory
   * @return new FFmpegInstaller
   */
  public static RTSPInstaller create(final Path executable) {
    return new RTSPInstaller(executable);
  }

  /**
   * Constructs a new RTSPInstaller with the default directory for the executable.
   * <p>
   * For Windows, it is C:/Program Files/static-emc Otherwise, it is [user home
   * directory]/static-emc
   *
   * @return new FFmpegInstaller
   */
  public static RTSPInstaller create() {
    return new RTSPInstaller();
  }

  @Override
  public boolean isSupported() {
    return true;
  }
}
