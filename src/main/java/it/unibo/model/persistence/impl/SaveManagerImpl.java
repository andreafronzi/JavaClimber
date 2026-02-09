package it.unibo.model.persistence.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;

/**
 * Implementation of {@link SaveManager} interface
 */
public class SaveManagerImpl implements SaveManager {

    private final String filePath;
    private final Gson gson;

    public SaveManagerImpl(final String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public SaveManagerImpl() {
        this(System.getProperty("user.home") + File.separator + "java_climber_save.json");
    }

    @Override
    public void save(SaveState state) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(state, writer);
        } catch (Exception e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    public Optional<SaveState> load() {
        final File file = new File(filePath);
        if (!file.exists()) {
            return Optional.empty();
        }
        try (Reader reader = new FileReader(file)) {
            return Optional.ofNullable(gson.fromJson(reader, SaveState.class));
        } catch (Exception e) {
            System.err.println("Failed to load game: " + e.getMessage());
            return Optional.empty();
        }
    }

}
