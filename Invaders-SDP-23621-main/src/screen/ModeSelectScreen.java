package screen;

import java.awt.event.KeyEvent;
import engine.Core;
import engine.Cooldown;

/**
 * Play 선택 후 일반 플레이와 보스 챌린지를 선택하는 화면.
 */
public final class ModeSelectScreen extends Screen {

    public static final int SCREEN_CODE = 8;

    public static int bossLevel = 5;
    
    private static final String[] OPTIONS = {
        "Normal Play",
        "Boss Challenge : stage 5",
        "Boss Challenge : stage 10"
    };

    private static final int[] NEXT_SCREEN_CODES = {
        2,
        9,
        9
    };

    /** 현재 선택한 항목: 0 = 일반, 1 = 보스 leve 5, 2 = 보스 level 10 */
    private int selectedIndex;

    private final Cooldown selectionCooldown = Core.getCooldown(200);

    public ModeSelectScreen(
            final int width,
            final int height,
            final int fps) {
        super(width, height, fps);
    }

    @Override
    public void initialize() {
        super.initialize();

        selectedIndex = 0;
        returnCode = 1;

        inputDelay.reset();
        selectionCooldown.reset();
    }

    @Override
    public int run() {
        // Screen.run()이 update()를 반복 호출한다.
        // isRunning이 false가 되면 아래 반환문으로 진행한다.
        super.run();
        return returnCode;
    }

    @Override
    protected void update() {
        super.update();

        if (inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                finish(1);

            } else if (selectionCooldown.checkFinished()) {
                if (isUpDown()) {
                    selectedIndex = Math.floorMod(
                            selectedIndex - 1, OPTIONS.length);
                    selectionCooldown.reset();

                } else if (isDownDown()) {
                    selectedIndex = (selectedIndex + 1) % OPTIONS.length;
                    selectionCooldown.reset();

                } else if (isConfirmDown()) {
                    if (selectedIndex == 1) {
                        bossLevel = 5;
                    } else if (selectedIndex == 2) {
                        bossLevel = 10;
                    }

                    finish(NEXT_SCREEN_CODES[selectedIndex]);
                }
            }
        }

        draw();
    }

    private boolean isUpDown() {
        return inputManager.isKeyDown(KeyEvent.VK_UP)
                || inputManager.isKeyDown(KeyEvent.VK_W);
    }

    private boolean isDownDown() {
        return inputManager.isKeyDown(KeyEvent.VK_DOWN)
                || inputManager.isKeyDown(KeyEvent.VK_S);
    }

    private boolean isConfirmDown() {
        return inputManager.isKeyDown(KeyEvent.VK_ENTER)
                || inputManager.isKeyDown(KeyEvent.VK_SPACE);
    }

    /**
     * 화면을 종료하고 Core에 전달할 코드를 설정한다.
     */
    private void finish(final int nextScreenCode) {
        returnCode = nextScreenCode;
        isRunning = false;
    }

    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawScreenTitle(this, "Select Mode");

        int firstRowY = height / 2;
        int rowSpacing = 50;

        for (int i = 0; i < OPTIONS.length; i++) {
            drawManager.drawMenuRow(
                    this,
                    OPTIONS[i],
                    firstRowY + i * rowSpacing,
                    selectedIndex == i);
        }

        drawManager.drawKeyHints(
                this,
                "W/S or arrows move, Enter/Space select, Esc back");

        drawManager.completeDrawing(this);
    }
}