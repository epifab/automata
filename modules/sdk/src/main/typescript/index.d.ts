export interface Cell {
  x: number,
  y: number,
  alive: boolean
}

export function runGameOfLife(
  width: number,
  height: number,
  delays: number,
  callback: (board: Cell[]) => void
)
