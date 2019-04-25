import re
import subprocess
import time
import urllib.request

from bs4 import BeautifulSoup

thunder_path = r"D:\Program Files (x86)\Thunder Network\Thunder9\Program\Thunder.exe"
max_length = 2


def download_with_thunder(file_url):
    subprocess.call([thunder_path, file_url])


def get_url():
    request = urllib.request.Request('https://www.meijutt.com/content/meiju23621.html')
    response = urllib.request.urlopen(request)
    html = response.read()
    soup = BeautifulSoup(html)
    # urlList = soup.findAll(attrs={"class": "down_url","value":re.compile("^ed2k")})
    parant_element = soup.findAll(attrs={"class": "tabs-list current-tab"})
    url_div_list = parant_element[0].findAll(attrs={"class": "down_url", "value": re.compile("^ed2k")})
    if len(url_div_list) > max_length:
        down_url = url_div_list[max_length]
        return down_url["value"]
    return None


if __name__ == '__main__':
    while 1:
        url = get_url()
        if None != url:
            print("正在下载：" + str(url).split("|")[2])
            max_length = max_length + 1
            download_with_thunder(url)
        time.sleep(1)
