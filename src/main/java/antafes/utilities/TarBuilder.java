/*
 * This file is part of Utilities.
 *
 * Utilities is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Utilities is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Utilities. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package Utilities
 * @author Marian Pollzien <map@wafriv.de>
 * @copyright (c) 2024, Marian Pollzien
 * @license https://www.gnu.org/licenses/lgpl.html LGPLv3
 */

package antafes.utilities;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TarBuilder
{
    private final OutputStream tarStream;
    private final boolean gzip;

    public TarBuilder(OutputStream tarStream, boolean gzip)
    {
        this.tarStream = tarStream;
        this.gzip = gzip;
    }

    public boolean tar(ArrayList<File> files)
    {
        AtomicBoolean successful = new AtomicBoolean(true);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(this.tarStream))
        {
            TarArchiveOutputStream tar = new TarArchiveOutputStream(
                this.gzip ? new GzipCompressorOutputStream(outputStream) : outputStream
            );

            files.forEach((file) -> {
                try {
                    TarArchiveEntry entry = tar.createArchiveEntry(file, file.getName());
                    tar.putArchiveEntry(entry);
                    Files.copy(file.toPath(), tar);
                    tar.closeArchiveEntry();
                } catch (IOException ignored) {
                    successful.set(false);
                }
            });

            if (successful.get()) {
                tar.finish();
            }
        } catch (IOException e) {
            successful.set(false);
        }

        return successful.get();
    }
}
