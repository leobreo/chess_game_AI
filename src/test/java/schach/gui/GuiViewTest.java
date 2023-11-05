package schach.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GuiViewTest {
    @Test
    public void testAccessToImageResources() {
        assertNotNull(getClass().getResourceAsStream("/images/kb.png"));
        assertNotNull(getClass().getResourceAsStream("/images/kw.png"));
        assertNotNull(getClass().getResourceAsStream("/images/qw.png"));
        assertNotNull(getClass().getResourceAsStream("/images/qb.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/bb.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/bw.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/rb.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/rw.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/nb.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/nw.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/pw.png"));
        // assertNotNull(getClass().getResourceAsStream("/images/pb.png"));
        // TODO: TestAccessToResourceBundles
    }
}
