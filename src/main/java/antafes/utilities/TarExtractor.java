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

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TarExtractor
{
    private final InputStream tarStream;
    private final boolean gzip;
    private final Path destination;

    public TarExtractor(InputStream tarStream, boolean gzip, Path destination)
    {
        this.tarStream = tarStream;
        this.gzip = gzip;
        this.destination = destination;
    }

    public void untar() throws IOException
    {
        try (BufferedInputStream inputStream = new BufferedInputStream(this.tarStream);
             TarArchiveInputStream tar = new TarArchiveInputStream(
                 this.gzip ? new GzipCompressorInputStream(inputStream) : inputStream)) {
            ArchiveEntry entry;
            while ((entry = tar.getNextEntry()) != null) {
                Path extractTo = this.destination.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(extractTo);
                } else {
                    Files.copy(tar, extractTo);
                }
            }
        }
    }
}
