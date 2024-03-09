import {Stage} from "@pixi/react";
import Cell from "./Cell.tsx";

export interface CellData {
  x: number,
  y: number,
  alive: boolean
}

interface BoardProps {
  board: CellData[]
  boardWidth: number
  boardHeight: number
}

function Board({board, boardWidth, boardHeight}: BoardProps) {
  const cellWidth = window.innerWidth / boardWidth
  const cellHeight = (window.innerHeight - 50) / boardHeight

  return (
    <Stage width={window.innerWidth} height={window.innerHeight - 30}>
      <>
        {board.map(cell =>
            <Cell
              top={cellHeight * cell.y}
              right={cellWidth * (cell.x + 1)}
              bottom={cellHeight * (cell.y + 1)}
              left={cellWidth * cell.x}
              alive={cell.alive}
              key={`${cell.x}-${cell.y}`}
            />
        )}
      </>
    </Stage>
  )
}

export default Board