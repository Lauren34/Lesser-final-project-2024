package lesser.finalproject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lesser.finalproject.json.ArtObject;
import lesser.finalproject.json.CollectionResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class RijksSearchFrame extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JPanel resultsPanel;
    private JButton nextPageButton;
    private JButton prevPageButton;
    private JLabel statusLabel;
    private RijksService rijksService;
    private int currentPage = 1;
    private CompositeDisposable disposable = new CompositeDisposable();

    public RijksSearchFrame() {
        setTitle("Rijksmuseum Art Search");
        setSize(800, 800);  // Increased height for better viewing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchField = new JTextField(30);
        searchButton = new JButton("Search");
        resultsPanel = new JPanel(new GridBagLayout());  // Use GridBagLayout for better control
        nextPageButton = new JButton("Next Page");
        prevPageButton = new JButton("Previous Page");
        statusLabel = new JLabel(" ");

        JPanel topPanel = new JPanel();
        topPanel.add(searchField);
        topPanel.add(searchButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JPanel navPanel = new JPanel();
        navPanel.add(prevPageButton);
        navPanel.add(nextPageButton);

        bottomPanel.add(navPanel, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        RijksServiceFactory factory = new RijksServiceFactory();
        rijksService = factory.getService();

        searchButton.addActionListener(e -> performSearch());
        nextPageButton.addActionListener(e -> nextPage());
        prevPageButton.addActionListener(e -> prevPage());

        performSearch();
    }

    private void performSearch() {
        String query = searchField.getText();
        Single<CollectionResponse> response;

        if (query.isEmpty()) {
            response = rijksService.pageNumber(currentPage, "1ljDF5e5");
        } else {
            response = rijksService.searchCollection(query, currentPage, "1ljDF5e5");
        }

        disposable.add(response.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(this::displayResults, this::handleError));
    }

    private void displayResults(CollectionResponse response) {
        resultsPanel.removeAll();

        ArtObject[] artObjects = response.artObjects;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        int displayCount = Math.min(10, artObjects.length);  // Ensure only 10 pictures are shown
        for (int i = 0; i < displayCount; i++) {
            ArtObject art = artObjects[i];
            try {
                URL imageUrl = new URL(art.webImage.url);
                Image image = ImageIO.read(imageUrl);
                Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  // Scale the image
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                JLabel label = new JLabel(imageIcon);
                label.setToolTipText(art.title + " by " + art.principalOrFirstMaker);

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new ImageFrame(art.webImage.url, art.title);
                    }
                });

                gbc.gridx = i % 4;  // 4 columns
                gbc.gridy = i / 4;  // Increment row after every 4 images
                resultsPanel.add(label, gbc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private void handleError(Throwable error) {
        error.printStackTrace();
        statusLabel.setText("Error fetching data");
    }

    private void nextPage() {
        currentPage++;
        performSearch();
    }

    private void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            performSearch();
        }
    }

    public static void main(String[] args) {

        new RijksSearchFrame().setVisible(true);

    }
}
