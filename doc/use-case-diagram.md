## Chess Use-Case Diagram 

```plantuml
left to right direction
actor PlayerGUI
actor PlayerCLI
PlayerGUI -|> PlayerCLI
rectangle "Chess" {
  PlayerCLI -- (Match against Computer)
  PlayerCLI -- (Match against Human)
  PlayerCLI -- (Load Game)
  PlayerCLI -- (Save Game)
  PlayerCLI -- (Quit)
  PlayerCLI -- (Make Move)
  PlayerCLI -- (Show Lost Pieces)
  PlayerGUI -- (Rotate Board)
  PlayerGUI -- (Settings)
  PlayerGUI -- (Apply Color)
  (Make Move) <-- (Promote Piece) : <<extend>>
  (Match against Human) <-- (Network Match) : <<extend>>
  (Match against Computer) <- (Apply Color) : <<include>>
}
```
