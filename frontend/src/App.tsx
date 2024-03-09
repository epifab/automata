import {useEffect, useState} from 'react'
import './App.css'
import {runGameOfLife} from 'life'
import Board, {CellData} from "./Board.tsx";

export function App() {

  const boardWidth =
    (window.innerWidth > window.innerHeight)
      ? 100
      : Math.ceil(window.innerWidth / window.innerHeight * 100)

  const boardHeight =
    (window.innerWidth > window.innerHeight)
      ? Math.ceil(window.innerHeight / window.innerWidth * 100)
      : 100

  const emptyBoard = Array(boardWidth * boardHeight)
    .fill(undefined)
    .map((_, i) => {
        return {
          x: Math.floor(i / boardHeight),
          y: i % boardHeight,
          alive: false
        }
      }
    );

  function randomBoard() {
    return emptyBoard.map((cell: CellData) => {
      return {
        ...cell,
        alive: Math.random() > 0.5
      }
    })
  }

  const defaultStop = () => {
    console.log("Nothing to stop")
  }

  const [board, setBoard] = useState<CellData[]>(() => emptyBoard);
  const [stop, setStop] = useState<() => void>(() => defaultStop);
  const [active, setActive] = useState<boolean>(false);

  function activate(newBoard: CellData[]) {
    if (!active) {
      const cancel = runGameOfLife(newBoard, 200, setBoard)
      setStop(() => cancel)
      setActive(true)
      return cancel
    }
  }

  function deactivate() {
    if (active) {
      console.log(board)
      stop()
      setStop(() => defaultStop)
      setActive(false)
    }
  }

  function resume() {
    console.log(board)
    activate(board)
  }

  function restart() {
    activate(randomBoard())
  }

  function cleanBoard() {
    deactivate()
    setBoard(emptyBoard)
  }

  // useEffect(() => {
  //   const cancel = activate(randomBoard())
  //   return () => cancel()
  // }, []);

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

