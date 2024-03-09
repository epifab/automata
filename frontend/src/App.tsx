import {useEffect, useState} from 'react'
import './App.css'
import {runGameOfLife} from 'life'
import Board, {CellData} from "./Board.tsx";

export function App() {

  const boardWidth = 100
  const boardHeight = Math.ceil(window.innerHeight / window.innerWidth * boardWidth)

  function emptyBoard() {
    return Array(boardWidth * boardHeight)
      .fill(undefined)
      .map((_, i) => {
          return {
            x: i % boardWidth,
            y: Math.floor(i / boardWidth),
            alive: false
          }
        }
      );
  }

  function randomBoard() {
    return emptyBoard().map((cell: CellData) => {
      return {
        ...cell,
        alive: Math.random() > 0.5
      }
    })
  }

  const defaultStop = () => {
    console.log("Nothing to stop")
  }

  const [board, setBoard] = useState<CellData[]>(randomBoard());
  const [stop, setStop] = useState<() => void>(() => defaultStop);
  const [active, setActive] = useState<boolean>(true);

  function start(newBoard: CellData[]) {
    if (active) {
      stop()
    }
    const cancel = runGameOfLife(newBoard, 200, setBoard)
    setStop(() => cancel)
    setActive(true)
    return cancel
  }

  function deactivate() {
    if (active) {
      setActive(false)
      stop()
      setStop(() => defaultStop)
    }
  }

  function resume() {
    start(board)
  }

  function restart() {
    start(randomBoard())
  }

  function cleanBoard() {
    deactivate()
    setBoard(emptyBoard)
  }

  useEffect(() => {
    if (active) {
      const cancel = start(randomBoard())
      return () => cancel()
    }
  }, []);

  return (
    <>
      <Board board={board} boardWidth={boardWidth} boardHeight={boardHeight}/>
      <div className="footer">
        {active
          ? <button onClick={deactivate}>stop</button>
          : <>
            <button onClick={resume}>resume</button>
            <button onClick={restart}>re-start</button>
            <button onClick={cleanBoard}>clean</button>
          </>
        }
      </div>
    </>
  )
}

