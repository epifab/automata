#!/bin/bash

rm -rf modules/sdk/target
sbt test sdk/fullLinkJS
cp modules/sdk/target/scala-3.4.0/sdk-opt/main.js modules/sdk/src/main/typescript/index.js
cd modules/sdk/src/main/typescript
npm link
cd ../../../../../frontend
npm install
npm link life
npm run build
npm run dev
