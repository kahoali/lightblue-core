package com.redhat.lightblue.util.metrics;

public class NoopRequestMetrics implements RequestMetrics {

    private static final NoopContext NOOP_CONTEXT = new NoopContext();

    @Override
    public Context startEntityRequest(String operation, String entity, String version) {
        return NOOP_CONTEXT;
    }

    @Override
    public Context startStreamingEntityRequest(String operation, String entity, String version) {
        return NOOP_CONTEXT;
    }
    
    @Override
    public Context startLockRequest(String lockOperation, String domain) {
        return NOOP_CONTEXT;
    }

    @Override
    public Context startBulkRequest(String bulkOperation, String entity, String version) {
        return NOOP_CONTEXT;
    }

    @Override
    public Context startHealthRequest(String operation) {
        return NOOP_CONTEXT;
    }

    @Override
    public Context startDiagnosticsRequest(String operation) {
        return NOOP_CONTEXT;
    }

    @Override
    public void setBulkRequest(boolean bulkRequest) {
        
    }

    @Override
    public boolean isBulkRequest() {
        return false;
    }
    
    private static class NoopContext implements Context {
        @Override
        public void endRequestMonitoring() {

        }

        @Override
        public void markRequestException(Exception e) {

        }

        @Override
        public void endRequestMonitoringWithException(Exception e) {
    
        }
    }
}
