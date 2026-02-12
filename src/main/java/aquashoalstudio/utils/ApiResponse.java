package aquashoalstudio.utils;

    public class ApiResponse<T> {
        private int statusCode;
        private T body;

        public ApiResponse(int statusCode, T body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        public int getStatusCode() { return statusCode; }
        public T getBody() { return body; }
    }

