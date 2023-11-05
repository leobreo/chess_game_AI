package schach.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Locale;
import java.util.ResourceBundle;

public class Settings {
    private BooleanProperty touchMoveRuleEnabled;
    private BooleanProperty informOfCheckEnabled;
    private BooleanProperty automaticRotateEnabled;

    private BooleanProperty highlightLegalMovesEnabled;

    private ResourceBundle bundleENG;
    private ResourceBundle bundleGER;
    private ResourceBundle bundleBCS;

    private String language;

    private int searchDepth;


    /**
     * Instantiate Settings
     */
    public Settings() {
        touchMoveRuleEnabled = new SimpleBooleanProperty();
        informOfCheckEnabled = new SimpleBooleanProperty();
        automaticRotateEnabled = new SimpleBooleanProperty();
        highlightLegalMovesEnabled = new SimpleBooleanProperty();
        loadLanguageResources();
        searchDepth = 2;
    }

    public boolean touchMoveRule() {
        return touchMoveRuleEnabled.get();
    }

    public boolean informOfCheck() {
        return informOfCheckEnabled.get();
    }

    /**
     * Get language resources
     * @return
     */
    public ResourceBundle getBundle() {
        if ("German".equals(language)) {
            return bundleGER;
        }
        return bundleENG;
    }

    /**
     * Search depth for AI alpha beta pruning
     * @return
     */
    public int getSearchDepth() {
        return searchDepth;
    }

    /**
     * Setter for AI search depth
     * @param searchDepth
     */
    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    /**
     * Set current game language
     * @param language
     */
    void setLanguage(String language)  {
       this.language = language;
    }

    /**
     * Load all existing langauge resources
     */
    private void loadLanguageResources() {
        bundleENG = ResourceBundle.getBundle("language-bundle", new Locale("en", "US"));
        bundleGER = ResourceBundle.getBundle("language-bundle", new Locale("de", "DE"));
        // TODO: Check if these are correct locale initialization strings
        bundleBCS = ResourceBundle.getBundle("language-bundle", new Locale("rs", "RS"));
    }

    /**
     * Touch rule move boolean
     * @return
     */
    public boolean isTouchMoveRuleEnabled() {
        return touchMoveRuleEnabled.get();
    }

    /**
     * Touch rule property
     * @return
     */
    public BooleanProperty touchMoveRuleEnabledProperty() {
        return touchMoveRuleEnabled;
    }

    /**
     * Inform of check boolean
     * @return
     */
    public boolean isInformOfCheckEnabled() {
        return informOfCheckEnabled.get();
    }

    /**
     * Inform of check property
     * @return
     */
    public BooleanProperty informOfCheckEnabledProperty() {
        return informOfCheckEnabled;
    }

    /**
     * Automatic rotate boolean
     * @return
     */
    public boolean isAutomaticRotateEnabled() {
        return automaticRotateEnabled.get();
    }

    /**
     * Automatic Rotate Enabled property
     * @return
     */
    public BooleanProperty automaticRotateEnabledProperty() {
        return automaticRotateEnabled;
    }

    /**
     * Is highlighting boolean
     * @return
     */
    public boolean isHighlightLegalMovesEnabled() {
        return highlightLegalMovesEnabled.get();
    }

    /**
     * highlight boolean
     * @return
     */
    public BooleanProperty highlightLegalMovesEnabledProperty() {
        return highlightLegalMovesEnabled;
    }

}
