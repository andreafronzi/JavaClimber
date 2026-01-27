package JavaClimber.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.persistence.impl.SaveManagerImpl;

/**
 * Tests for {@link SaveManager}.
 */
public class SaveManagerTest {

    private static final String TEST_FILE = "test_save.json";
    private SaveManager saveManager = new SaveManagerImpl(TEST_FILE);

    /**
     * After tests delete the file if exist.
     */
    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Tests for saving, loading and check the correction of data.
     */
    @Test
    void testSaveAndLoad(){
        final SaveState originalState = new SaveState(1500, 5000, Set.of("s_basic", "s_astro"), Map.of("pt_jump1",3), "s_atro");
        saveManager.save(originalState);

        final Optional<SaveState> loadedState = saveManager.load();
        assertTrue(loadedState.isPresent());

        assertEquals(originalState.getCoins(), loadedState.get().getCoins());
        assertEquals(originalState.getHighestScore(), loadedState.get().getHighestScore());
        assertEquals(originalState.getOwnedItems(), loadedState.get().getOwnedItems());
        assertEquals(originalState.getConsumables(), loadedState.get().getConsumables());
        assertEquals(originalState.getSelectedSkin(), loadedState.get().getSelectedSkin());
    }

    /**
     * Tests for load a missing file.
     */
    @Test
    void testLoadMissingFile() {
        final SaveManager missingFileManager = new SaveManagerImpl("non_existent_file.json");
        final Optional<SaveState> result = missingFileManager.load();
        assertTrue(result.isEmpty());
    }
}
