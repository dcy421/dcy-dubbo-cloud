import axios from '@/libs/api.request'

/**
 * 添加${cfg.modulesName}
 * @param ${cfg.modulesApi}
 * @returns {ClientHttp2Stream | * | AxiosPromise<any> | ClientRequest | void}
 */
export const add${cfg.modules} = (${cfg.modulesApi}) => {
  return axios.request({
    url: '/admin-service/${cfg.modulesApi}/save',
    method: 'post',
    data: ${cfg.modulesApi}
  })
}

/**
 * 修改${cfg.modulesName}
 * @param ${cfg.modulesApi}
 * @returns {ClientHttp2Stream | * | AxiosPromise<any> | ClientRequest | void}
 */
export const update${cfg.modules} = (${cfg.modulesApi}) => {
  return axios.request({
    url: '/admin-service/${cfg.modulesApi}/update',
    method: 'post',
    data: ${cfg.modulesApi}
  })
}

/**
 * 删除${cfg.modulesName}
 * @param ${cfg.modulesApi}Id
 * @returns {ClientHttp2Stream | * | AxiosPromise<any> | ClientRequest | void}
 */
export const delete${cfg.modules}ById = (${cfg.modulesApi}Id) => {
  return axios.request({
    url: '/admin-service/${cfg.modulesApi}/delete',
    method: 'post',
    params: {
      id: ${cfg.modulesApi}Id
    }
  })
}

/**
 * 批量删除${cfg.modulesName}
 * @param ${cfg.modulesApi}Ids
 * @returns {ClientHttp2Stream | * | AxiosPromise<any> | ClientRequest | void}
 */
export const deleteBatch${cfg.modules}ById = (${cfg.modulesApi}Ids) => {
  return axios.request({
    url: '/admin-service/${cfg.modulesApi}/deleteBatch',
    method: 'post',
    data: ${cfg.modulesApi}Ids
  })
}
