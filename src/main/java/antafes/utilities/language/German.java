/*
 * This file is part of tdeBuildingCosts.
 *
 * tdeBuildingCosts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * tdeBuildingCosts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with tdeBuildingCosts. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package tdeBuildingCosts
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
public class German extends Language {
    /**
     * Create a new english language object.
     */
    public German() {
        this.languages();
        this.general();
        this.menu();
        this.about();
    }

    /**
     * List of available languages.
     */
    private void languages() {
        this.getTranslations().put("english", "Englisch");
        this.getTranslations().put("german", "Deutsch");
    }

    /**
     * General texts like the programs title.
     */
    private void general() {
        this.getTranslations().put("title", "DSA Gebäudekostenrechner");
        this.getTranslations().put("cancel", "Abbrechen");
        this.getTranslations().put("next", "Weiter");
        this.getTranslations().put("ok", "Ok");
        this.getTranslations().put("back", "Zurück");
        this.getTranslations().put("finish", "Fertig");
        this.getTranslations().put("other", "Anderes");
        this.getTranslations().put("description", "Beschreibung");
    }

    /**
     * Texts for the menu entries.
     */
    private void menu() {
        this.getTranslations().put("file", "Datei");
        this.getTranslations().put("fileMnemonic", "D");
        this.getTranslations().put("quit", "Beenden");
        this.getTranslations().put("quitMnemonic", "B");
        this.getTranslations().put("help", "Hilfe");
        this.getTranslations().put("helpMnemonic", "H");
        this.getTranslations().put("about", "Über");
        this.getTranslations().put("aboutMnemonic", "b");
        this.getTranslations().put("new", "Neu");
        this.getTranslations().put("newMnemonic", "N");
        this.getTranslations().put("open", "Öffnen");
        this.getTranslations().put("openMnemonic", "f");
        this.getTranslations().put("save", "Speichern");
        this.getTranslations().put("saveMnemonic", "S");
        this.getTranslations().put("existingFile", "Existierende Datei");
        this.getTranslations().put("fileExists", "Die ausgewählte Datei existiert bereits, überschreiben?");
        this.getTranslations().put("couldNotLoad", "Laden fehlgeschlagen");
        this.getTranslations().put("loading", "Lade Datei...");
        this.getTranslations().put("close", "Schließen");
    }

    /**
     * Content of the about dialog.
     */
    private void about() {
        this.getTranslations().put("aboutText", "Dieses Programm wurde von Marian Pollzien erstellt.");
    }

    /**
     * Get the language.
     *
     * @return
     */
    @Override
    public String getLanguage() {
        return "German";
    }
}
