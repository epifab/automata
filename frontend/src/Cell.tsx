import {Graphics} from "@pixi/react";
import {memo} from "react";

interface CellProps {
  top: number
  right: number
  bottom: number
  left: number
  alive: boolean
}

const Cell = memo(
  function Cell({top, left, bottom, right, alive}: CellProps) {
    return (
      <Graphics draw={(g) => {
        g.beginFill(alive ? 0xffffff : 0x999999);
        g.moveTo(left, top)
        g.lineTo(right, top)
        g.lineTo(right, bottom)
        g.lineTo(left, bottom)
        g.lineTo(left, top)
        g.endFill();
      }}/>
    )
  }
)

export default Cell