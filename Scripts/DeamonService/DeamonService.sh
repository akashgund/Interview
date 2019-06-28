#shell script to create and run application as Deamon
sudo groupadd -r appmgr

sudo useradd -r -s /bin/false -g appmgr jvmapps


sudo chown -R jvmapps:appmgr /home/akash/Desktop/DeamonService/

cd /etc/systemd/system/

sudo touch myapp.service
sudo chmod 777 myapp.service
sudo cat > /etc/systemd/system/myapp.service << EOF
[Unit]
Description= MyApp java Service
[Service]
WorkingDirectory= ../../InterviewApplication/build/libs
ExecStart=/bin/java -Xms256m -Xmx512m -jar demo-0.0.1-SNAPSHOT.jar
User=jvmapps
Type=simple
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF


sudo systemctl daemon-reload

sudo systemctl start myapp.service

sudo systemctl status myapp
