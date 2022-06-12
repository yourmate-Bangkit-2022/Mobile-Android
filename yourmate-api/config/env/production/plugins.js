module.exports = {
    upload: {
        config: {
            provider: '@strapi-community/strapi-provider-upload-google-cloud-storage',
            providerOptions: {
                bucketName: 'gs://yourmate.appspot.com',
                publicFiles: true,
                uniform: false,
                basePath: '',
            },
        },
    },
}