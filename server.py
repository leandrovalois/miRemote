from http.server import BaseHTTPRequestHandler, HTTPServer
import os

class MiStickHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        command = self.path[1:]  # Remove a barra inicial
        device_id = "192.168.0.144:5555"  # Substitua pelo ID do seu mi stick
        if command == "up":
            os.system(f"adb -s {device_id} shell input keyevent 19")
        elif command == "down":
            os.system(f"adb -s {device_id} shell input keyevent 20")
        elif command == "left":
            os.system(f"adb -s {device_id} shell input keyevent 21")
        elif command == "right":
            os.system(f"adb -s {device_id} shell input keyevent 22")
        elif command == "ok":
            os.system(f"adb -s {device_id} shell input keyevent 23")
        elif command == "back":
            os.system(f"adb -s {device_id} shell input keyevent 4")
        elif command == "home":
            os.system(f"adb -s {device_id} shell input keyevent 3")
        elif command == "power":
            os.system(f"adb -s {device_id} shell input keyevent 26")  # Power
        elif command == "volumedown":
            os.system(f"adb -s {device_id} shell input keyevent 25")  # Volume -
        elif command == "volumeup":
            os.system(f"adb -s {device_id} shell input keyevent 24")  # Volume +
        self.send_response(200)
        self.end_headers()

server = HTTPServer(('0.0.0.0', 8080), MiStickHandler)
server.serve_forever()
