import {useEffect, useState} from 'react'
import './App.css'
import {GameOfLife} from 'scalajs:main.js'
import {Graphics, Stage} from '@pixi/react';

interface CellData {
  x: number,
  y: number,
  alive: boolean
}

interface BoardProps {
  board: CellData[]
  cellWidth: number
  cellHeight: number
}

export function App() {
  const [board, setBoard] = useState<CellData[]>([])

  const boardWidth = 100
  const boardHeight = 100
  const cellWidth = window.innerWidth / boardWidth
  const cellHeight = window.innerHeight / boardHeight

  useEffect(() => {
    const cleanup = GameOfLife.run(boardWidth, boardHeight, 1, (b: CellData[]) => setBoard(b))
    console.info("Board loaded")
    return () => {
      console.info("Board unloaded")
      cleanup()
    }
  }, []);

  return (
    <Board board={board} cellWidth={cellWidth} cellHeight={cellHeight} />
  )
}

function Board({ board, cellWidth, cellHeight }: BoardProps) {
  return (
    <Stage width={window.innerWidth} height={window.innerHeight}>
      <Graphics draw={(g) => {
        board.forEach(cell => {
          const top = cellHeight * cell.y
          const bottom = cellHeight * (cell.y + 1)
          const left = cellWidth * cell.x
          const right = cellWidth * (cell.x + 1)
          g.beginFill(cell.alive ? 0xffffff : 0x999999);
          g.moveTo(left, top)
          g.lineTo(right, top)
          g.lineTo(right, bottom)
          g.lineTo(left, bottom)
          g.lineTo(left, top)
          g.endFill();
        })
      }} />
    </Stage>
  )
}
