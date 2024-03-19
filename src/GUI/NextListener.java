package GUI;

public class NextListener implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click event (e.g., left-click)
        System.out.println("Mouse clicked on the button!");
        // Create a GamePanel and add it to the main window
        GamePanel gamePanel = new GamePanel();
        switchToScreen(gamePanel);
    }
}
