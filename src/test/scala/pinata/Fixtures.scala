package pinata

// format: off
object Fixtures {
  val start: String = "🚪"
  val goal: String = "🪅"

  val obstacles: Set[String] =
    Set("🎉", "🎊", "🥳", "🎈", "🎂", "🍰", "🎁", "🎤", "🕺", "💃", "🎵", "🎶", "🎧", "🎠", "🎙", "📸", "🎇", "🎆", "👯", "🕴", "🤳", "🙌")

  val foods: Set[String] =
    Set("🍕", "🍔", "🍟", "🌮", "🍣", "🍱", "🍤", "🧀", "🥪", "🥭", "🧁", "🍩", "🍪", "🍫", "🍿", "🍦", "🍧", "🍨", "🍹", "🥂", "🍺", "🍻", "🥤", "🍷", "🥃")

  val board: Board[String] = Board(
    Array(
      Array("📸", "🎵", "🎊", "🎈", "🎵", "👯", "🤳", "🎂", "💃", "💃", "🍰", "🤳", "🥳", "🎶", "💃", "👯", "🥳", "🎠", "🎠", "📸", "🕴", "🎠", "🍰", "🎵", "🥳", "🎊", "🍰", "👯", "🎉", "🎤", "🤳", "💃", "🎈", "🎙", "🥳"),
      Array("🙌", "🚪", "🎂", "🍻", "🍤", "🍪", "🥳", "🥤", "🍿", "🍷", "🍕", "🥃", "🍨", "🍫", "🍺", "🍱", "🎵", "🌮", "🍨", "🥤", "🥂", "🍩", "📸", "🍩", "🥭", "🍧", "🧀", "🧆", "🍷", "🥂", "🍷", "🧆", "🍦", "🍟", "🎶"),
      Array("🎇", "🍱", "🎙", "🎇", "🎂", "🍱", "🎤", "🍔", "🎵", "🍫", "🎂", "📸", "👯", "🎤", "🍰", "🥤", "🎇", "🥭", "🤳", "🎊", "🙌", "🍣", "🎆", "🎵", "🎉", "💃", "🎉", "🍦", "👯", "🙌", "🤳", "🎂", "💃", "🍨", "🎙"),
      Array("💃", "🍤", "🥤", "🥃", "💃", "🍻", "🧁", "🍔", "🎂", "🍫", "🍟", "🧁", "🍣", "🍦", "🎙", "🍟", "🍷", "🍔", "🎠", "🍩", "🎂", "🥭", "🙌", "🍔", "🍩", "🍤", "📸", "🍧", "🍱", "🥤", "🥤", "🍦", "🕴", "🍣", "🤳"),
      Array("🎤", "🎧", "🙌", "🍦", "🎙", "👯", "🎂", "👯", "🎁", "🍰", "🙌", "🎁", "🎆", "🍟", "🎉", "🎵", "💃", "🎤", "🎊", "🌮", "💃", "🍔", "🕴", "🍣", "🎠", "🥂", "🥳", "🎵", "💃", "🎁", "🎇", "🙌", "🎶", "🍹", "🎉"),
      Array("🎇", "🍹", "🎈", "🥃", "🥳", "🍫", "🍿", "🍦", "🍨", "🍧", "👯", "🍪", "🍨", "🍺", "🙌", "🍤", "🥭", "🧁", "🍕", "🌮", "🕴", "🍩", "🍕", "🍫", "👯", "🥂", "🍿", "🍱", "🍔", "🥃", "🎊", "🌮", "🧁", "🍩", "🙌"),
      Array("🎊", "🥤", "🎂", "🍱", "🎁", "🍷", "🎆", "💃", "🎉", "🍕", "🎆", "🌮", "🤳", "🕴", "🎇", "🍹", "💃", "🎙", "🎉", "🍫", "🎵", "🎵", "🎉", "🎂", "🎉", "🤳", "🙌", "🍰", "🎤", "🍩", "🎂", "🍣", "🎊", "🍿", "🎉"),
      Array("💃", "🍹", "🎙", "🥂", "🎇", "🥂", "🧆", "🍨", "🎵", "🍕", "🍫", "🥂", "🎂", "🍱", "🍦", "🌮", "🎇", "🧆", "🍺", "🍷", "🥂", "🥂", "🍕", "🧀", "🍨", "🍩", "🕴", "🍫", "🍩", "🍿", "🎶", "🥤", "🙌", "🍧", "🙌"),
      Array("🤳", "🍣", "🎶", "🍿", "🤳", "🙌", "🍰", "🍷", "🎶", "🎶", "🎊", "🥳", "📸", "🍔", "🎂", "🍔", "🍰", "🍰", "🎠", "🎉", "🎁", "🎁", "📸", "🤳", "🎆", "🎙", "🎵", "🍱", "💃", "🎇", "🕴", "🍺", "🎠", "🍨", "🎧"),
      Array("🕴", "🍿", "🍩", "🌮", "👯", "🥤", "🥭", "🍤", "💃", "🥭", "🍫", "🥭", "🎈", "🍟", "💃", "🍕", "🍔", "🍹", "🥤", "🥭", "🍿", "🌮", "🍣", "🧁", "🙌", "🧆", "🌮", "🍪", "🎊", "🍷", "🥂", "🍧", "👯", "🍺", "💃"),
      Array("🎇", "🍷", "💃", "🙌", "🥳", "🧁", "💃", "🎙", "🎆", "🧀", "🙌", "👯", "🎶", "🍩", "🎠", "🎠", "🥳", "🙌", "👯", "🕴", "🎆", "📸", "🎈", "🍺", "🎶", "🍤", "🎧", "🥳", "🎤", "🍨", "💃", "🧁", "🎠", "🎊", "🎧"),
      Array("🎉", "🍷", "🍹", "🍿", "🍹", "🧆", "📸", "🍤", "🧁", "🍤", "🎆", "🍔", "🍔", "🍩", "💃", "🍨", "🍤", "🧁", "🍕", "🍧", "🍦", "🥤", "🎉", "🍧", "🎙", "🍕", "🎆", "🍦", "🎊", "🍻", "🎠", "🍿", "🍣", "🍣", "🎇"),
      Array("🎙", "🎈", "🎈", "👯", "🎇", "🎇", "🕴", "🍕", "💃", "🎤", "📸", "🧆", "🥳", "🙌", "🎁", "👯", "🎊", "💃", "🎊", "🍷", "💃", "🤳", "🤳", "🍤", "🎵", "🍟", "🙌", "🍿", "🎙", "🧆", "🤳", "🎂", "🤳", "🍤", "🙌"),
      Array("🎉", "🍩", "🥂", "🍧", "🧆", "🍕", "🍻", "🍨", "🎆", "🍧", "🧀", "🌮", "🎵", "🍹", "🍫", "🥂", "🍿", "🧁", "🥭", "🍩", "🥳", "🍟", "🧀", "🍺", "🎊", "🍧", "🎤", "🧆", "🥃", "🍩", "🍪", "🍔", "🎙", "🍕", "🙌"),
      Array("🙌", "🍣", "🎆", "🍱", "🥳", "🎊", "🙌", "🧁", "🍰", "🍩", "📸", "🕴", "🎙", "🍔", "🎈", "🎊", "🎶", "🎆", "🙌", "🥂", "🥳", "🍺", "🎧", "🍪", "🎊", "🥭", "🎊", "🎁", "🤳", "💃", "🎈", "🍹", "🎧", "🍱", "🎤"),
      Array("🎈", "🍷", "🙌", "🍻", "🎠", "🍿", "💃", "🍫", "🎇", "🍷", "🤳", "🥃", "🎈", "🥃", "🍔", "🥭", "🎶", "🍧", "🍹", "🍣", "👯", "🍟", "🎤", "🍹", "🥳", "🍨", "🍤", "🍷", "🥳", "🍧", "🍟", "🧁", "🎶", "🧁", "🎧"),
      Array("🎶", "🧀", "🎶", "🧆", "🙌", "🧀", "🙌", "🍦", "🎶", "🍷", "💃", "🥂", "🙌", "🍣", "🎂", "🍨", "🍰", "🧀", "🎶", "🙌", "📸", "🧁", "🎆", "🍰", "🎵", "🎆", "🥳", "🍫", "💃", "🎂", "🙌", "🥭", "🎊", "🍷", "🎙"),
      Array("🙌", "🌮", "🎙", "🌮", "🎤", "🍷", "🧀", "🍔", "🥳", "🧁", "🎠", "🍟", "🍟", "🥃", "🕴", "🍫", "🎈", "🥃", "🍫", "🌮", "🍰", "🍻", "🍔", "🍣", "🥂", "🍫", "🎠", "🍩", "🍟", "🍔", "🎉", "🍩", "🎠", "🧀", "🙌"),
      Array("🎇", "🥃", "🎠", "🍺", "🎆", "🎶", "🎉", "🥳", "🕴", "🍹", "🙌", "🍺", "🤳", "🕴", "🎙", "🍨", "🎇", "🍤", "🎇", "🕴", "🎂", "👯", "🎵", "🥭", "🎈", "📸", "🎉", "🤳", "🕴", "🍷", "🎁", "🎉", "🎵", "🍕", "🎇"),
      Array("🎶", "🍧", "🕴", "🍣", "🍷", "🍿", "🥃", "🥃", "🍷", "🍺", "🎁", "🍦", "👯", "🧆", "🧁", "🍤", "📸", "🍔", "💃", "🍹", "🥭", "🍻", "💃", "🧁", "💃", "🍱", "🍤", "🥭", "🎵", "🧁", "🍷", "🥭", "🍧", "🍿", "🎉"),
      Array("🎉", "🍱", "🎇", "🕴", "🎉", "🎈", "👯", "🤳", "🎵", "🤳", "🎁", "🍿", "📸", "🍟", "💃", "🤳", "🎠", "🍩", "🤳", "🥭", "🎧", "🧆", "🎈", "🍟", "🎂", "🍕", "🎧", "🥭", "🎤", "🎂", "🎁", "💃", "🎙", "🍺", "🎊"),
      Array("🎵", "🍣", "🌮", "🍕", "🥂", "🍻", "🌮", "🍫", "🎧", "🍪", "🎁", "🍧", "🤳", "🍷", "🧀", "🍩", "👯", "🍣", "🙌", "🧀", "🎉", "🍧", "🍿", "🍻", "🎁", "🍪", "🎇", "🍕", "🧆", "🌮", "🎆", "🍦", "🧆", "🍧", "🍰"),
      Array("🎇", "🍰", "🎠", "👯", "🎆", "🙌", "🥳", "🌮", "💃", "🍩", "💃", "🍣", "🥳", "🤳", "🙌", "🍹", "🙌", "🥳", "🎧", "🍩", "📸", "🎆", "🎶", "💃", "🎁", "🍷", "🎉", "🎂", "💃", "🥃", "👯", "🧀", "🎧", "🎙", "🤳"),
      Array("🎙", "🧁", "🍪", "🍤", "🎠", "🥂", "🎧", "🍱", "🙌", "🍔", "🍟", "🧆", "🎶", "🍔", "💃", "🍷", "🍨", "🍕", "👯", "🍕", "🍺", "🍪", "🍱", "🍹", "🧆", "🍕", "🎶", "🥃", "🎂", "🍤", "🎊", "🍹", "🥃", "🥭", "🍰"),
      Array("🍰", "🌮", "👯", "🍷", "🎊", "🧀", "🙌", "🍺", "🎈", "🍻", "🕴", "🎶", "🎇", "🍟", "🙌", "🕴", "🎶", "🍣", "🎙", "🎧", "🎈", "🥳", "🕴", "🎁", "🕴", "💃", "🎵", "🌮", "🎊", "🌮", "🥳", "🎇", "🥳", "🥭", "🎵"),
      Array("🥳", "🥭", "💃", "🍻", "🍦", "🍷", "🎊", "🥃", "👯", "🍷", "🍔", "🍨", "🍦", "🍩", "🎤", "🥂", "🍤", "🍔", "🍪", "🍫", "🧁", "🥃", "🍺", "🍷", "🍫", "🍩", "🙌", "🍫", "🍰", "🥃", "🌮", "🍦", "🎆", "🍷", "🤳"),
      Array("🎧", "🍩", "🥳", "🤳", "🎊", "🎙", "🎇", "🧆", "💃", "🍪", "📸", "💃", "🤳", "💃", "🍰", "🍺", "🎂", "📸", "📸", "🎊", "🎁", "🎵", "🎤", "🤳", "🕴", "🍧", "🎂", "🍷", "🎙", "🌮", "🎇", "🍹", "🎠", "🍟", "🎠"),
      Array("🥳", "🌮", "🍺", "🍻", "🍿", "🧀", "💃", "🍤", "🎇", "🧆", "🎙", "🌮", "🍣", "🧆", "🎉", "🍻", "🍹", "🍣", "🙌", "🥭", "🎆", "🍪", "🌮", "🍟", "🍩", "🍧", "🎈", "🍤", "🎈", "🥤", "🎊", "🍦", "🎆", "🍕", "🎵"),
      Array("🎉", "🥂", "🎂", "🎙", "💃", "🥭", "🎙", "🌮", "🎉", "💃", "🥳", "🥭", "🎵", "🍣", "🎇", "🎇", "👯", "🍧", "🥳", "🍩", "🙌", "🍺", "🎊", "🎵", "🍰", "👯", "🍰", "🥭", "📸", "🍔", "🎵", "🎁", "🎊", "🥭", "🕴"),
      Array("👯", "🍩", "🍻", "🥭", "🥳", "🍧", "🎊", "🍨", "🍔", "🧆", "🍻", "🍩", "🎂", "🍟", "🍷", "🧆", "🥂", "🥂", "🎈", "🧀", "🍰", "🧁", "🎙", "🧆", "🍦", "🍤", "🥤", "🥂", "🎇", "🍪", "🍤", "🧆", "🧆", "🧆", "🤳"),
      Array("🎇", "🎉", "🎈", "🥃", "🙌", "🍹", "🤳", "💃", "🎤", "🎶", "🎧", "💃", "🎁", "🕴", "🎉", "🎤", "💃", "🎠", "🥳", "🧁", "💃", "🍺", "🤳", "💃", "🎉", "🍺", "🎉", "🥂", "📸", "🥳", "🎆", "🎂", "🎧", "💃", "🕴"),
      Array("🎇", "🍤", "🎶", "🥤", "🎠", "🍕", "🍨", "🧁", "🥭", "🍷", "🍤", "🧀", "🍨", "🍻", "🎂", "🍤", "🍦", "🧆", "🍣", "🧁", "👯", "🍹", "🍪", "🥭", "👯", "🍦", "🎠", "🧆", "🍷", "🧁", "🎁", "🍿", "🍔", "🍺", "📸"),
      Array("🙌", "🍦", "🕴", "🍩", "🎁", "🙌", "🍰", "🎂", "🕴", "🍿", "👯", "🍰", "🎁", "🎧", "🎙", "🍻", "🤳", "🤳", "🎤", "🍣", "🍰", "🎶", "🙌", "🍷", "🎵", "💃", "🎉", "🍿", "🕴", "🍪", "💃", "💃", "🎠", "🍧", "📸"),
      Array("📸", "🍔", "🍱", "🍕", "🍱", "🥂", "🍱", "🍧", "🎊", "🍦", "🍹", "🍟", "🍪", "🍿", "🥂", "🌮", "🥂", "🍩", "🎠", "🍨", "🌮", "🍩", "🍕", "🍤", "🥤", "🧀", "🌮", "🍔", "🎙", "🍧", "🍤", "🍩", "🍕", "🪅", "🎧"),
      Array("🎉", "🥳", "🎙", "💃", "🎊", "🎶", "🎠", "🎊", "🎶", "🎁", "🕴", "🙌", "📸", "🎇", "🎤", "🎠", "💃", "📸", "🎙", "👯", "🍰", "🎶", "👯", "🎧", "🎁", "💃", "🎁", "🕴", "🎈", "🎉", "🎊", "🙌", "📸", "👯", "💃")
    )
  )
}
// format: on