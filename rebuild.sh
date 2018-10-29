#!/usr/bin/env bash
#**************************************************************************** #
#                                                                              #
#                                                             |\               #
#    rebuild.sh                                         ------| \----          #
#                                                       |    \`  \  |  p       #
#    By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o       #
#                                                       |---\  \   `|  l       #
#    Created: 2018/05/31 21:54:47 by jeudy2552          | ` .\  \   |  y       #
#    Updated: 2018/05/31 21:54:47 by jeudy2552          -------------          #
#                                                                              #
# **************************************************************************** #

clear
cd src/
rm -r classes/
mkdir -p classes/
javac -d classes/ files/ACM.java files/ACMObject.java && javac -cp classes/ -d classes/ files/ACMTest.java
echo "Rebuild complete"
echo "use './run' to launch"
