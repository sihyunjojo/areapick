import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function getRequest(url) {
    return await local.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error(`Error fetching ${url}:`, error);
        throw error;
      });
  }


async function getGu() {
    return await getRequest(`/api/v1/map/gu`);
  }
  
  /**
   * 동 데이터를 가져오는 함수
   * @param {string} code - 동 코드
   * @param {object} headers - 추가할 헤더 정보 (선택 사항)
   * @returns {Promise} - 동 데이터를 가져오는 Promise
   */
  async  function getDong(code) {
    return await getRequest(`/api/v1/map/dong/${code}`);
  }
  
  /**
   * 지역 데이터를 가져오는 함수
   * @param {string} code - 지역 코드
   * @param {object} headers - 추가할 헤더 정보 (선택 사항)
   * @returns {Promise} - 지역 데이터를 가져오는 Promise
   */
  async  function getArea(code) {
    return await getRequest(`/api/v1/map/area/${code}`);
  }

  async function getAreaInfo(code){
    return await getRequest(`api/map/info/area/${code}`);
  }

export {
    getGu,
    getDong,
    getArea,
    getAreaInfo
}
