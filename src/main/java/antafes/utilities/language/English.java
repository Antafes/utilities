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
package antafes.utilities.language;

/**
 * Language class for english.
 *
 * @author Marian Pollzien
 */
public class English extends Language {
    /**
     * Create a new english language object.
     */
    public English() {
        this.languages();
        this.general();
        this.menu();
        this.about();
    }

    /**
     * List of available languages.
     */
    private void languages() {
        this.getTranslations().put("english", "English");
        this.getTranslations().put("german", "German");
    }

    /**
     * General texts like the programs title.
     */
    private void general() {
        this.getTranslations().put("title", "Vampire Editor");
        this.getTranslations().put("darkAgesVampire", "Dark Ages: Vampire");
        this.getTranslations().put("cancel", "Cancel");
        this.getTranslations().put("next", "Next");
        this.getTranslations().put("ok", "Ok");
        this.getTranslations().put("back", "Back");
        this.getTranslations().put("finish", "Finish");
        this.getTranslations().put("other", "Other");
        this.getTranslations().put("description", "Description");
    }

    /**
     * Texts for the menu entries.
     */
    private void menu() {
        this.getTranslations().put("file", "File");
        this.getTranslations().put("fileMnemonic", "F");
        this.getTranslations().put("quit", "Quit");
        this.getTranslations().put("quitMnemonic", "Q");
        this.getTranslations().put("help", "Help");
        this.getTranslations().put("helpMnemonic", "H");
        this.getTranslations().put("about", "About");
        this.getTranslations().put("aboutMnemonic", "A");
        this.getTranslations().put("new", "New");
        this.getTranslations().put("newMnemonic", "N");
        this.getTranslations().put("open", "Open");
        this.getTranslations().put("openMnemonic", "O");
        this.getTranslations().put("save", "Save");
        this.getTranslations().put("saveMnemonic", "S");
        this.getTranslations().put("print", "Print");
        this.getTranslations().put("printMnemonic", "P");
        this.getTranslations().put("existingFile", "Existing file");
        this.getTranslations().put("fileExists", "The selected file already exists, overwrite?");
        this.getTranslations().put("couldNotLoad", "Could not load");
        this.getTranslations().put("loading", "Loading file...");
        this.getTranslations().put("close", "Close");
    }

    /**
     * Content of the about dialog.
     */
    private void about() {
        this.getTranslations().put("aboutText", "This tool was created by Marian Pollzien.");
    }

    /**
     * Get the language.
     *
     * @return The language
     */
    @Override
    public String getLanguage() {
        return "English";
    }
}
