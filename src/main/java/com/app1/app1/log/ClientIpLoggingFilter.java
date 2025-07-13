package com.app1.app1.log;

import java.util.logging.Filter;

public class ClientIpLoggingFilter implements Filter {
    private static final String MDC_KEY = "clientIp";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String ip = extractClientIp(request); // ฟังก์ชันดูข้อ 3

        MDC.put(MDC_KEY, ip); // ➜ ให้ Logback ดึงได้ด้วย %X{clientIp}
        try {
            chain.doFilter(req, res); // ทำงาน ต่อ
        } finally {
            MDC.remove(MDC_KEY); // ล้างทิ้ง ไม่งั้น thread reuse แล้ว IP ติดไปกับคนอื่น
        }
    }

    /** คืนค่า IP จริง โดยเช็ก header ที่ proxy/cloudflare/ALB มักจะตั้งมาให้ */
    private String extractClientIp(HttpServletRequest request) {
        String[] HEADERS = {
                "X-Forwarded-For", "X-Real-IP",
                "Proxy-Client-IP", "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
        };
        for (String h : HEADERS) {
            String ip = request.getHeader(h);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                // บางที header มีหลาย IP คั่นด้วย ‘,’ — เอาตัวหน้าสุด
                return ip.split(",")[0].trim();
            }
        }
        return request.getRemoteAddr(); // ไม่ผ่าน proxy
    }

}
