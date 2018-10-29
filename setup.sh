#!/usr/bin/env bash
# **************************************************************************** #
#                                                                              #
#                                                             |\               #
#    setup.sh                                           ------| \----          #
#                                                       |    \`  \  |  p       #
#    By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o       #
#                                                       |---\  \   `|  l       #
#    Created: 2018/05/31 21:38:14 by jeudy2552          | ` .\  \   |  y       #
#    Updated: 2018/05/31 21:38:14 by jeudy2552          -------------          #
#                                                                              #
# **************************************************************************** #

clear
cd src/
mkdir -p classes
cd ..
chmod +x run.sh
chmod +x rebuild.sh
cd src/
javac -d classes/ files/ACM.java && javac -cp classes/ -d classes/ files/ACMTest.java
echo "Setup finished."
echo "Use './run.sh' to run"
