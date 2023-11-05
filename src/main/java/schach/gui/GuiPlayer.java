package schach.gui;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import schach.Game;
import schach.Player;
import schach.Position;
import schach.move.Move;
import schach.move.PromotionMove;
import schach.piece.Piece.Color;
import schach.piece.Queen;

public class GuiPlayer extends Player {
    private Object lock = new Object();
    private Game game;
    private Set<Move> moves;
    private Optional<Position> source = Optional.empty();
    private GuiView view;
    private Optional<Move> chosenMove = Optional.empty();

    /**
     * Instantiate a human player
     * @param color
     * @param view
     * @param game
     */
    public GuiPlayer(Color color, GuiView view, Game game) {
        super(color);
        this.game = game;
        this.view = view;
        // view.mainStage.setOnCloseRequest(null);
        view.getBoard().setOnSquareSelected(this::update);
    }

    /**
     * Update Game state
     * @param pos
     */
    private void update(Position pos) {
        if (playerColor != game.getCurrentPlayerColor()) {
            return;
        }
        if (source.isEmpty()) {
            moves = game.getLegalMoves(getPlayerColor());
            moves.removeIf(m -> !m.getSource().equals(pos));
            if (view.getSettings().isHighlightLegalMovesEnabled()) {
                view.getBoard().showPossibleTargets(
                    moves.stream().map(m -> m.getTarget()).collect(Collectors.toSet()));
            }
            source = Optional.of(pos);
            view.getBoard().getSquare(pos.getRow(), pos.getCol()).toggleStroke();
        } else {
            moves.removeIf(m -> !m.getTarget().equals(pos));
            if (moves.contains(new PromotionMove(source.get(), pos, new Queen(playerColor)))) {
                chosenMove = Optional.of(new PromotionMove(
                    source.get(), pos, view.promptPlayerForPromotionPiece(playerColor)));
            } else {
                chosenMove = moves.stream().findAny();
            }
            synchronized (lock) {
                lock.notify();
            }
            view.getBoard().showPossibleTargets(Set.of());
            view.getBoard().getSquare(source.get().getRow(), source.get().getCol()).toggleStroke();
            source = Optional.empty();
        }
    }

    @Override
    public Optional<Move> chooseMove() {
        view.getBoard().requestRotation(playerColor == Color.Black);
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            return Optional.empty();
        }
        return chosenMove;
    }
}
