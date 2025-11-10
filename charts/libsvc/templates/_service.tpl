{{- define "libsvc.service" -}}
apiVersion: v1
kind: Service
metadata:
  name: {{ include "libsvc.fullname" . }}
  labels:
    {{- include "libsvc.labels" . | nindent 4 }}
spec:
  type: {{ .Values.service.type }}
  selector:
    {{- include "libsvc.labels" . | nindent 4 }}
  ports:
    - port: {{ .Values.service.port }}
      targetPort: {{ .Values.service.targetPort }}
      protocol: TCP
      name: http
{{- end -}}
