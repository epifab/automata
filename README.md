# Automata

HERE BE DRAGONS

playground for scala js + typescript

## Requirements

- sbt
- npm

### Run

```shell
cd frontend
npm run dev
```

Changes made to ts files make vide reload, changes made to scala require manual reload,
at times you need to clean and re-compile:

```shell
find . -name target -type d -exec rm -rf {} \;
sbt clean cleanFiles fastLinkJS
cd frontend
npm run dev
```
