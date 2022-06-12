'use strict';

/**
 * tourism service.
 */

const { createCoreService } = require('@strapi/strapi').factories;

module.exports = createCoreService('api::tourism.tourism');
