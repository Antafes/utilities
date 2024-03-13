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
 * @copyright (c) 2020, Marian Pollzien
 * @license https://www.gnu.org/licenses/lgpl.html LGPLv3
 */

package antafes.utilities;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Configuration data
 */
public class Configuration extends BaseConfiguration {
    @Override
    public String getBasePath() {
        return System.getProperty("user.home") + "/";
    }

    @Override
    public Language getLanguage() {
        return (Language) super.getLanguage();
    }

    @Getter
    public enum Language implements LanguageInterface {
        ENGLISH ("antafes.utilities.language.English", "English", "images/english.png"),
        GERMAN ("antafes.utilities.language.German", "German", "images/german.png");

        private final String languageString;
        private final String name;
        private final ImageIcon icon;

        Language(String languageString, String name, String iconPath) {
            this.languageString = languageString;
            this.name = name;

            Toolkit kit = Toolkit.getDefaultToolkit();
            Image img = kit.createImage(
                Utilities.getResourceInJar(iconPath)
            );
            this.icon = new ImageIcon(img);
        }
    }
}