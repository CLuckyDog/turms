<template>
    <content-template
        :name="name"
        :url="url"
        :filters="filters"
        :table="table"
        :selectable="false"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template';

export default {
    name: 'access-control-admin-action-log-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'admin-action-log',
            url: this.$rs.apis.adminActionLog,
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('adminActionLogId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'accounts',
                    placeholder: this.$t('adminAccount'),
                    decorator: {
                        nonSpace: true
                    }
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'logDate'
                }
            ],
            table: {
                columns: [{
                    title: this.$t('adminActionLogId'),
                    key: 'id',
                    width: '15%'
                },
                {
                    title: this.$t('adminAccount'),
                    key: 'account',
                    width: '12.5%'
                },
                {
                    key: 'ip',
                    width: '12.5%'
                },
                {
                    key: 'logDate',
                    width: '15%'
                },
                {
                    key: 'action',
                    width: '15%'
                },
                {
                    title: this.$t('parameter'),
                    key: 'params',
                    width: '15%'
                },
                {
                    key: 'body',
                    width: '15%'
                }]
            }
        };
    }
};
</script>